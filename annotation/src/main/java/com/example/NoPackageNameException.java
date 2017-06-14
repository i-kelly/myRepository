package com.example;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example
 *  @文件名:   NoPackageNameException
 *  @创建者:   Admin
 *  @创建时间:  2017/6/3 12:42
 *  @描述：    TODO
 */

import javax.lang.model.element.TypeElement;

public class NoPackageNameException
        extends Exception {

    public NoPackageNameException(TypeElement typeElement) {
        super("The package of " + typeElement.getSimpleName() + " has no name");
    }
}
