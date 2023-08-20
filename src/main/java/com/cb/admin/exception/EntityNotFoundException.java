package com.cb.admin.exception;

public class EntityNotFoundException extends RuntimeException{
  public EntityNotFoundException(String entityName) {
    super("Entity not found. Entity Name: " + entityName);
  }
}
