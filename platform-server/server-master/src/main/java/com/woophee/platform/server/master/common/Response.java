package com.woophee.platform.server.master.common;

public class Response extends RestResult<String> {
    public Response() {
        super();
    }
    private Response(boolean state){
        super(state);
    }
    public static Response failed(ErrorType errorType, String errorMessage){
        return Response.failed(errorType, errorMessage, null);
    }

    public static Response failed(ErrorType errorType, String errorMessage, String errorStack){
        Response response = new Response(false);
        response.setErrorType(errorType);
        response.setErrorMessage(errorMessage);
        response.setErrorStack(errorStack);
        return response;
    }

    public static Response succeed(){
        return SUCCESS_RESPONSE;
    }

    public static final Response SUCCESS_RESPONSE= new UnmodifiableResponse(true);

    private static class UnmodifiableResponse extends Response {
        public UnmodifiableResponse(boolean state) {
            super(state);
        }

        @Override
        public void setState(Boolean state) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setData(String data) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setErrorType(ErrorType errorType) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setErrorMessage(String errorMessage) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setErrorStack(String errorStack) {
            throw new UnsupportedOperationException();
        }
    }
}
