/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.suren.autotest.web.framework.selenium.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.suren.autotest.web.framework.core.ElementSearchStrategy;
import org.suren.autotest.web.framework.core.ui.Element;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * 元素查找策略规则
 * @author suren
 * @date Jul 17, 2016 8:04:51 AM
 */
@Component
public class SearchStrategyUtils implements ApplicationContextAware
{
	private ApplicationContext context;
	private Map<String, String> strategyMap = new HashMap<String, String>();
	
	public SearchStrategyUtils()
	{
		strategyMap.put("priority", "prioritySearchStrategy");
		strategyMap.put("cyle", "cyleSearchStrategy");
		strategyMap.put("zone", "zoneSearchStrategy");
	}
	
	@SuppressWarnings("unchecked")
	public <T> ElementSearchStrategy<T> findStrategy(Class<T> type, Element element)
	{
		String strategy = element.getStrategy();
		strategy = StringUtils.isBlank(strategy) ? "prioritySearchStrategy" : strategyMap.get(strategy);
		
		return (ElementSearchStrategy<T>) context.getBean(strategy, ElementSearchStrategy.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException
	{
		this.context = context;
	}
	
	/**
	 * @return 所有的策略
	 */
	public Set<String> getAllStrategy()
	{
		return strategyMap.keySet();
	}
}
