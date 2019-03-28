package com.myapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.myapp.entity.Region;
import com.myapp.entity.Request;
import com.myapp.entity.Team;
import com.myapp.exception.RequestNotFoundException;
import com.myapp.service.WebscrapeAPIService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="/api", produces ="application/json")
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WebscrapeAPIController {
	
	public static final Logger logger = LoggerFactory.getLogger(WebscrapeAPIController.class);

	@Autowired
	WebscrapeAPIService webScrapeAPIService;

	@ApiOperation(value = "get all regions", response = Region.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Region Details Retrieved", response = Region.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Region not found") })
	@GetMapping("/region")
	public ResponseEntity<List<Region>> getAllRegions() {
		
		logger.info(">>>>> getAllRegions() service method called");
		
		List<Region> regionList = webScrapeAPIService.getAllRegions();
		
		if (regionList.isEmpty() || regionList == null) {
			throw new RequestNotFoundException("No Regions found");
		}
		
		return new ResponseEntity<List<Region>>(regionList, HttpStatus.OK);
	}

	@ApiOperation(value = "get team by region id", response = Team.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Team Details Retrieved", response = Team.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Team not found") })
	@GetMapping("/team/{regionId}")
	public ResponseEntity<List<Team>> getTeamByRegionId(@PathVariable("regionId") Integer regionId) {

		logger.info(">>>>> getTeamByRegionId service method called for region id : " + regionId);
		
		List<Team> teamList = webScrapeAPIService.getTeamByRegionId(regionId);

		if (teamList.isEmpty() || teamList == null) {
			throw new RequestNotFoundException("Region id not found : " + regionId);
		}

		return new ResponseEntity<List<Team>>(teamList, HttpStatus.OK);
	}

	@ApiOperation(value = "create new request")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Request created"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping("/request")
	public ResponseEntity<Void> addRequest(@RequestBody Request request, UriComponentsBuilder builder) {

		logger.info(">>>>> addRequest service method called");
		logger.debug(">>>>> addRequest service method called. Request object is : " + request.toString());
		
		webScrapeAPIService.addRequest(request);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/request/{id}").buildAndExpand(request.getRequestId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "get all requests", response = Request.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request Details Retrieved", response = Request.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Request not found") })
	@GetMapping("/request")
	public ResponseEntity<List<Request>> getAllRequests() {
		
		logger.info(">>>>> getAllRequests service method called");
		
		List<Request> requestList = webScrapeAPIService.getAllRequests();
		
		if (requestList.isEmpty() || requestList == null) {
			throw new RequestNotFoundException("Requests not found in the database");
		}
		
		return new ResponseEntity<List<Request>>(requestList, HttpStatus.OK);
	}

	@ApiOperation(value = "get request by request id", response = Request.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request Details Retrieved for the given request id", response = Request.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Request not found") })
	@GetMapping("/request/{requestId}")
	public ResponseEntity<Request> getRequestByRequestId(@PathVariable("requestId") Integer requestId) {

		logger.info(">>>>> getRequestByRequestId service method called for request id : " + requestId);
		
		Request requestList = webScrapeAPIService.getRequestByRequestId(requestId);
		
		if (requestList == null) {
			throw new RequestNotFoundException("Request not found for given request id : " + requestId);
		}

		return new ResponseEntity<Request>(requestList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "delete request by request id")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Delete request for the given request id"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Request not found") })
	@DeleteMapping("/request/{requestId}")
	public ResponseEntity<Void> deleteRequest(@PathVariable("requestId") Integer requestId) {

		logger.info(">>>>> deleteRequest service method called for request id : " + requestId);
		
		webScrapeAPIService.deleteRequest(requestId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "update an existing request")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request updated"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Request not found") })
	@PutMapping("/request")
	public ResponseEntity<Request> updateRequestByRequestId(@RequestBody Request request) {
		
		logger.info(">>>>> updateRequestByRequestId service method called for request id : " + request.getRequestId());
		
		webScrapeAPIService.updateRequestByRequestId(request);
		
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}
	
}
