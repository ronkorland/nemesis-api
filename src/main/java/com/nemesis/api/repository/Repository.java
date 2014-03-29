package com.nemesis.api.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.nemesis.api.model.BaseModel;

public interface Repository<M extends BaseModel, I> {

	public M create(M model);

	public M save(M model);

	public M delete(M model);

	public M findOne(Query query);

	public M findById(I id);

	public List<M> findAll();
}
