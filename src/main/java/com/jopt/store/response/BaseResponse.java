/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jopt.store.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Mexico_City")
	private LocalDateTime timestamp; // Response TimeStamp (Date-Time)
	private Boolean success; // Response status suscces/faild (true/false)
	private String status; // Response Http Status Code (Name)
	private Integer code; // Response Http Status Code (Number)
	private String message; // Response Message / Error Message
	private T payload; // Response Data / Error Details

	// Base Response Builder
	public static class BaseResponseBuilder<T> {
		private HttpStatus httpStatus;
		private Boolean success;
		private String status;
		private Integer code;
		private String message;
		private T payload;

		public BaseResponseBuilder<T> setHttpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
			this.status = httpStatus.name();
			this.code = httpStatus.value();
			return this;
		}

		public BaseResponseBuilder<T> setSuccess(Boolean success) {
			this.success = success;
			return this;
		}

		public BaseResponseBuilder<T> setStatus(String status) {
			this.status = status;
			return this;
		}

		public BaseResponseBuilder<T> setCode(Integer code) {
			this.code = code;
			return this;
		}

		public BaseResponseBuilder<T> setMessage(String message) {
			this.message = message;
			return this;
		}

		public BaseResponseBuilder<T> setPayload(T payload) {
			this.payload = payload;
			return this;
		}

		public ResponseEntity<BaseResponse<T>> build() {
			BaseResponse<T> baseResponse = new BaseResponse<>(LocalDateTime.now(), this.success, this.status,
					this.code, this.message, this.payload);
			return new ResponseEntity<>(baseResponse, this.httpStatus);
		}
	}

}
