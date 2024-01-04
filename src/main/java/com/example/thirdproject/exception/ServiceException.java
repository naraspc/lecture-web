package com.example.thirdproject.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
    public static class EntityNotFoundException extends ServiceException {

        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class UnauthorizedException extends ServiceException {

        public UnauthorizedException(String message) {
            super(message);
        }
    }
}
