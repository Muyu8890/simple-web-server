package com.jeecode.core.query;

import com.jeecode.core.page.SimplePage;

import java.util.List;

/**
 * 页。包含result属性。
 */
public class Page<T> extends SimplePage implements java.io.Serializable {

	private static final long serialVersionUID = 80238317179585389L;

	/**
	 * 当前页的数据
	 */
	private List<T> data;


	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param data
	 *            分页内容
	 */
	public Page(int pageNo, int pageSize, int totalCount, List<T> data) {
		super(pageNo, pageSize, totalCount);
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Page{" +
				"pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", totalCount=" + totalCount +
				", data=" + data +
				'}';
	}
}
