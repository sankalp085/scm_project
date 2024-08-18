package com.scm.helper;

public class ResourceNotFoundexception extends RuntimeException{

  public ResourceNotFoundexception (String message){
    super(message);

  }
  public ResourceNotFoundexception(){
    super("Resource not found");

  }

}
