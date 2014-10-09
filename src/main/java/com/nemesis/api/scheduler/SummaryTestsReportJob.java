package com.nemesis.api.scheduler;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Style;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Th;
import com.hp.gagawa.java.elements.Tr;
import com.nemesis.api.data.suite.SuiteData;
import com.nemesis.api.data.suite.SuitesData;
import com.nemesis.api.model.User;
import com.nemesis.api.service.SuiteService;
import com.nemesis.api.service.UserService;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class SummaryTestsReportJob {

	private static final Logger logger = Logger.getLogger(SummaryTestsReportJob.class);

	@Autowired
	private SuiteService suiteService;

	@Autowired
	private UserService userService;

	private String nemesisUrl;

	private String from;

	public SummaryTestsReportJob(String nemesisUrl, String from) {
		this.nemesisUrl = nemesisUrl;
		this.from = from;
	}

	public void sendSummaryEmail() {
		String html = generateHtml();
		logger.debug("executeInternal html " + html);
		sendEmail(html);
	}

	public String generateHtml() {
		logger.debug("Start generateHtml");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		int totalTests = 0;
		int totalFailedTests = 0;
		int totalSkippedTests = 0;

		Document document = new Document(DocumentType.HTMLStrict);

		Table table = new Table();
		table.setAttribute("border", "1");

		Style style = new Style("text/css");
		style.appendText(".label-status-SUCCESS {color: green;}");
		style.appendText(".label-status-SKIP {color: orange;}");
		style.appendText(".label-status-FAILURE {color: red;}");

		Tr trHeader = getTableHeader();

		table.appendChild(trHeader);

		SuitesData last24HoursSuites = suiteService.findLast24HoursDistinct();
		if (last24HoursSuites != null && last24HoursSuites.getSuites() != null
				&& last24HoursSuites.getSuites().size() > 0) {
			for (SuiteData suite : last24HoursSuites.getSuites()) {
				totalTests = totalTests + suite.getNumberOfTests();
				totalFailedTests = totalFailedTests + suite.getNumberOfFails();
				totalSkippedTests = totalSkippedTests + suite.getNumberOfSkips();

				String link = nemesisUrl + "#/suite/" + suite.getId();
				Tr suiteRow = getSuiteRow(suite.getSuiteName(), suite.getStartTime().toString(formatter), suite
						.getEndTime().toString(formatter), suite.getRunningTime(), suite.getNumberOfTests(),
						suite.getHtmlStatus(), link);

				table.appendChild(suiteRow);
			}

			Tr summaryRow = getSummaryRow(totalTests, totalFailedTests, totalSkippedTests);
			table.appendChild(summaryRow);
		}

		document.body.appendChild(table);
		document.head.appendChild(style);

		logger.debug("End generateHtml");
		return document.write();
	}

	private Tr getSummaryRow(int totalTests, int totalFailedTests, int totalSkippedTests) {
		Tr trSummaryRow = new Tr();

		Td tdSummary = new Td();
		tdSummary.setAttribute("colspan", "7");
		tdSummary.appendText("Total Tests: " + totalTests + " Failed: " + totalFailedTests + " Skipped: "
				+ totalSkippedTests);

		trSummaryRow.appendChild(tdSummary);
		return trSummaryRow;
	}

	private Tr getTableHeader() {
		Tr trHeader = new Tr();
		Th thSuiteName = new Th();
		thSuiteName.appendText("Suite Name");

		Th thStartTime = new Th();
		thStartTime.appendText("Start Time");

		Th thEndTime = new Th();
		thEndTime.appendText("End Time");

		Th thElapsed = new Th();
		thElapsed.appendText("Elapsed (HH:MM:SS)");

		Th thTests = new Th();
		thTests.appendText("Tests");

		Th thStatus = new Th();
		thStatus.appendText("Status");

		Th thLink = new Th();
		thLink.appendText("Link");

		trHeader.appendChild(thSuiteName);
		trHeader.appendChild(thStartTime);
		trHeader.appendChild(thEndTime);
		trHeader.appendChild(thElapsed);
		trHeader.appendChild(thTests);
		trHeader.appendChild(thStatus);
		trHeader.appendChild(thLink);

		return trHeader;
	}

	private Tr getSuiteRow(String suiteName, String startTime, String endTime, String elapsed, int numberOfTests,
			String status, String link) {
		Tr trSuiteRow = new Tr();

		Td tdSuiteName = new Td();
		tdSuiteName.appendText(suiteName);

		Td tdStart = new Td();
		tdStart.appendText(startTime);

		Td tdEnd = new Td();
		tdEnd.appendText(endTime);

		Td tdElapsed = new Td();
		tdElapsed.appendText(elapsed);

		Td tdTests = new Td();
		tdTests.appendText(Integer.toString(numberOfTests));

		Td tdStatus = new Td();
		tdStatus.appendText(status);

		Td tdLink = new Td();
		A a = new A(link, "_blank", "Open");
		tdLink.appendChild(a);

		trSuiteRow.appendChild(tdSuiteName);
		trSuiteRow.appendChild(tdStart);
		trSuiteRow.appendChild(tdEnd);
		trSuiteRow.appendChild(tdElapsed);
		trSuiteRow.appendChild(tdTests);
		trSuiteRow.appendChild(tdStatus);
		trSuiteRow.appendChild(tdLink);
		return trSuiteRow;
	}

	private void sendEmail(String html) {
		List<User> users = userService.findUserDailyReport();

		if (users == null || users.size() < 1) {
			logger.error("Failed to get any user");
			return;
		}

		// Sender's email ID needs to be mentioned
		String fromEmail = this.from;

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", "localhost");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromEmail));

			// Set To: header field of the header.
			for (User user : users) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			}

			// Set Subject: header field
			message.setSubject("Daily Automation Summary Report - " + getCurrentTimeInFormat("yyyy-MM-dd"));

			// Send the actual HTML message, as big as you like
			message.setContent(html, "text/html");

			// Send message
			Transport.send(message);
			logger.info("Sent message successfully to: " + users);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static String getCurrentTimeInFormat(String format) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		return LocalDate.now().toString(formatter);
	}
}
