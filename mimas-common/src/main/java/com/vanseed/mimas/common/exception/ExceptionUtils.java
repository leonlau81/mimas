/**
 * 
 */
package com.vanseed.mimas.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author leon
 *
 */
public class ExceptionUtils {

    public static String getFormatExceptionMessage(Exception e) {
		List<String> exceptionStack = new ArrayList<String>();
		exceptionStack.add(e.getClass().getName());
		for (StackTraceElement ste : e.getStackTrace()) {
			exceptionStack.add(" at " + ste.getClassName() + "."
					+ ste.getMethodName() + "(" + ste.getFileName() + ":"
					+ ste.getLineNumber() + ")");
		}
		return StringUtils.collectionToDelimitedString(exceptionStack, "");
	}

    /**
     * 根据异常信息代码和异常信息参数初始化多条业务异常
     * @param ex
     */
    public static RuntimeException unchecked(Throwable ex)
    {
        if (ex instanceof RuntimeException)
        {
            return (RuntimeException) ex;
        }
        else
        {
            return new RuntimeException(ex);
        }
    }
}
