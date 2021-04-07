package com.mbvan.mbvan.common.webservice.jaxrs;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Patmbvanaram;
import javax.ws.rs.Produces;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.mbvan.mbvan.common.dto.ClientResponseWithHeader;
import com.mbvan.mbvan.common.dto.SingleDataClientResponse;

@Produces({ "application/json" })
@Path("/")
@Transactional(readOnly = true)
public interface BaseCrudJaxrs<T> {

	@GET
	@Path("/{id}")
	SingleDataClientResponse<T> get(@Patmbvanaram("id") Long id);

//	@DELETE
//	@Path("/{id}")
//	@Transactional
//	ClientResponseWithHeader delete(@Patmbvanaram("id") Long id);

	@POST
	@Transactional
	SingleDataClientResponse<T> create(T t);

	@PUT
	@Transactional
	SingleDataClientResponse<T> update(T t);

	@GET
	@Path("/list")
	SingleDataClientResponse<Iterable<T>> findAll();

	@GET
	@Path("/list/{pageNumber}/{pageSize}")
	SingleDataClientResponse<Page<T>> findByPage(@Patmbvanaram("pageNumber") int pageNumber,@Patmbvanaram("pageSize") int pageSize);
}
