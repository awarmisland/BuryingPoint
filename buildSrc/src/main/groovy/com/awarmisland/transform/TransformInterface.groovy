package com.awarmisland.transform

interface TransformInterface{
      boolean isModifyClass(String className)
      byte[] modifyClass(String className,byte[] classBytes)
//      void handleDirectoryInput(File file)
//      void handleJarInputs(JarOutputStream jarOutputStream,InputStream inputStream,String entryName);
}