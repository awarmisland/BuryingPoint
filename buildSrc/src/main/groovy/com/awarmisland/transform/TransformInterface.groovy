package com.awarmisland.transform

interface TransformInterface{
      boolean isModifyClass(String className)
      byte[] modifyClass(String className,byte[] classBytes)
}