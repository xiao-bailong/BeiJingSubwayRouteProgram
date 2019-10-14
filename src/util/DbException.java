package util;

public class DbException extends BaseException {
	public DbException(java.lang.Throwable ex){
		super("操作错误："+ex.getMessage());
	}
}
