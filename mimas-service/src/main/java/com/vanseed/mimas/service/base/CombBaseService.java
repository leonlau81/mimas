/**
 * 
 */
package com.vanseed.mimas.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leon
 *
 */
@Component
public class CombBaseService {
	@Autowired
	protected CombSessionService sessionService;
}
