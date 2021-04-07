package com.mbvan.mbvan.common.webservice.jaxrs.impl;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Patmbvanaram;
import javax.ws.rs.Produces;

import com.mbvan.mbvan.common.datahandle.DefaultResponseHandler;
import com.mbvan.mbvan.common.datahandle.ExceptionHandler;
import com.mbvan.mbvan.common.dto.SingleDataClientResponse;
import com.mbvan.mbvan.common.service.BaseCrudService;
import com.mbvan.mbvan.common.util.AssertUtil;


@Produces({ "application/json" })
@Path("/")
public abstract class DefaultBaseCrudJaxrsImpl<T>{

	protected abstract BaseCrudService<T> getService();

	@GET
	@Path("/{id}")
	public SingleDataClientResponse<T> get(@Patmbvanaram("id") Long id) {
		SingleDataClientResponse<T> response = null;
		try {
			T t= getService().findById(id);
			response = DefaultResponseHandler.generateSingleDataClientResponse(t);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
		return response;
	}

//	@DELETE
//	@Path("/{id}")
//	public ClientResponseWithHeader delete(@Patmbvanaram("id") Long id) {
//		getService().delete(id);
//		return DefaultResponseHandler.generateBlankResponse();
//	}

	@POST
	public SingleDataClientResponse<T> create(T t) {
		SingleDataClientResponse<T> response = null;
		try {
			AssertUtil.notNull(t, "entityBody");
			t= getService().create(t);
			response = DefaultResponseHandler.generateSingleDataClientResponse(t);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
		return response;
	}

	@PUT
	public SingleDataClientResponse<T> update(T t) {
		SingleDataClientResponse<T> response = null;
		try {
			t = getService().update(t);
			response = DefaultResponseHandler.generateSingleDataClientResponse(t);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
		return response;
	}

	@GET
	@Path("/list")
	public SingleDataClientResponse<Iterable<T>> findAll() {
		SingleDataClientResponse<Iterable<T>> response = null;
		try {
			Iterable<T> lt = getService().findAll();
			response = DefaultResponseHandler.generateSingleDataClientResponse(lt);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
		return response;
	}
	
//	@GET
//	@Path("/list/{pageNumber}/{pageSize}")
//	public SingleDataClientResponse<Page<T>> findByPage(@Patmbvanaram("pageNumber") int pageNumber,@Patmbvanaram("pageSize") int pageSize) {
//		Page<T> pt = getService().findByPage(pageNumber,pageSize);
//		SingleDataClientResponse<Page<T>> response = DefaultResponseHandler.generateSingleDataClientResponse(pt);
//		return response;
//	}
	
	
}
