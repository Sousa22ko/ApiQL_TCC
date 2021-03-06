package com.graphql.exemple.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import graphql.ExecutionInput;
import graphql.ExecutionResult;

@SuppressWarnings({"rawtypes","unchecked"})
public abstract class GenericController<T extends GenericEntity, RES extends GenericResolver> {

	@Autowired
	protected RES resolver;

	@RequestMapping
	public ResponseEntity<Object> query(@RequestBody String query) {

		ExecutionResult result = resolver.getGraphQL().execute(preProcessInput(query));
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	private ExecutionInput preProcessInput(String input) {

		LinkedTreeMap<String, Object> json = new Gson().fromJson(input, LinkedTreeMap.class);
		LinkedTreeMap<String, Object> variaveis = (LinkedTreeMap<String, Object>) json.get("variables");

		ExecutionInput query = new ExecutionInput((String)json.get("query"), null, this, "root", variaveis);
		return query;
	}

}
