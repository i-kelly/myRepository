package com.example.aop;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.aop
 *  @文件名:   CheckLoginAspect
 *  @创建者:   Admin
 *  @创建时间:  2017/6/3 18:02
 *  @描述：    TODO
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import util.T;

@Aspect
public class CheckLoginAspect {
    @Pointcut("execution(@com.example.CheckLogin * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
//        if (null == SPUtils.()) {
//            Snackbar.make(BaseApplication.getInstance().getCurActivity().getWindow().getDecorView(), "请先登录!", Snackbar.LENGTH_LONG)
//                    .setAction("登录", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
////                            TRouter.go(C.LOGIN);
//                        }
//                    }).show();
//            return;
//        }
        if(true){
            T.showShort("需要去登录-----");
//            return;
        }
        joinPoint.proceed();//执行原方法
    }
}
