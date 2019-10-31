package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;





public class Page<T> {
	
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	protected int pageNo = 1;//当前页号
	protected int pageSize = 10;//默认页面条数
	protected String orderBy = null;
	protected String order = null;	
	protected long totalCount = -1;
	protected long totalPage = -1;
	protected long startLine = 0;//起始行数

	//这个是结果集
	protected List<T> result = new ArrayList<T>();
	
	public Page(){
		
	}

	
	//设置页面的条数
	public Page(int pageSize){
		this.pageSize = pageSize;
	}
	
	//总的记录数
	public long getTotalCount() {
		return totalCount;
	}
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo(){
		return pageNo;
	}
	
	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}
	
	/**
	 * 获得每页的记录数量 ，默认为10
	 */
	public int getPageSize(){
		return pageSize;
	}
	/**
	 * 设置每页的记录数量
	 */
	public void setPageSize(final int pageSize){
		this.pageSize = pageSize;
	}
	/**
	 * 根据pageNo和pageSize计算当前页面第一条记录在总结果集中的位置，序号从1开始
	 */
	public int getFirst(){
		return ((pageNo - 1) * pageSize) + 1;
	}
	/**
	 * 获得排序字段，无默认值，多个排序字段用','分隔
	 */
	public void setOrderBy(final String orderBy){
		this.orderBy = orderBy;
	}
	/**
	 * 获得排序方向
	 */
	public String getOrder(){
		return order;
	}
	/**
	 * 设置排序方式向.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		//检查order字符串的合法值
		String[] orders = StringUtils.split(lowcaseOrder,",");
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = lowcaseOrder;
	}
	
	/**
	 * 返回Page对象自身的setOrder函数,可用于连续设置。
	 */
	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}
	
	/**
	 * 设置总页数.
	 */
	public void setTotalPage(final long totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPage() {
		if (totalCount < 0) {
			return -1;
		}

		this.totalPage = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			totalPage++;
		}
		return totalPage;
	}
	
	/**
	 * 根据pageNo与pageSize计算起始行数, 默认值为0.
	 */
	public long getStartLine(){
		if (pageNo == 1) {
			return 0;
		}
		
		this.startLine = (pageNo - 1) * pageSize;
		
		return startLine;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPage());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}
	
	/**
	 * 获得页面记录
	 * @return
	 */
	
	public List<T> getResult(){
		return result;
	}
	/**
	 * 设置页面记录
	 * @param result
	 */
	public void setResult(final List<T> result){
		this.result = result;
	}

	public String getOrderBy() {
		return orderBy;
	}
	
	
	/**
	 * 创建一个新的page
	 * @param pageNo   当前要显示的页数
	 * @param pageSize 每一页显示的数目
	 * @param totalCount 总的记录数
	 * */
	 public Page(int pageNo,int pageSize,int totalCount) {
		 this.pageNo = pageNo;
		 this.pageSize = pageSize;
		 this.totalCount = totalCount;
	 }
	
	 /**
	  * 从符合条件的所有记录数里面获得真正要显示的记录
	  * @param 符合条件的所有的记录
	  * 
	  * */
	 public void setResultOfPage(List result) {
			int start = (pageNo-1)*this.pageSize;
			for(int i = 0; i < this.getPageSize();i++) {
				if(i+1 > result.size()-start) {
					break;
				}
				this.result.add((T) result.get(start+i));
			}
	 }
	
	
	
	
	
	
	
	
	
	
	
	
}
