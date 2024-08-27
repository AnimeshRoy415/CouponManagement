package com.coupon.mgmt.ApiResponseModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public abstract class   BaseApiDelegate {


    protected <T> ResponseEntity<ApiResponse<T>> formApiResponse(T data) {
        return sendSuccessApiResponse(data, "");
    }

    protected <T> ResponseEntity<ApiResponse<T>> sendSuccessApiResponse(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(data);
        response.setStatus(true);
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    protected <T> ResponseEntity<? extends ApiResponse> sendFailedApiResponse(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(data);
        response.setStatus(false);
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
