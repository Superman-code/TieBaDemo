package org.xkj.util;

public class PageUtil {
	public static Page createPage(int everyPage, int totalCount, int currentPage) {
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrentPage(currentPage);
		int totalPage = getTotalPage(everyPage, totalCount);
		int beginIndex = getBeginIndex(currentPage, everyPage);
		boolean prePage = getPrePage(currentPage);
		boolean nextPage = getNextPage(currentPage, totalPage);
		return new Page(everyPage, totalPage, totalCount, currentPage, beginIndex, prePage, nextPage);
	}

	private static boolean getNextPage(int currentPage, int totalPage) {
		return currentPage == totalPage ? false : true;
	}

	private static boolean getPrePage(int currentPage) {
		return currentPage == 1 ? false : true;
	}

	private static int getBeginIndex(int currentPage, int everyPage) {
		return (currentPage - 1) * everyPage;
	}

	private static int getTotalPage(int everyPage, int totalCount) {
		int totalPage = 0;
		if (totalCount != 0 && totalCount % everyPage == 0) {
			if(totalCount < everyPage) {
				totalPage = totalCount / everyPage + 1;
			} else {
				totalPage = totalCount / everyPage;
			}
		} else {
			totalPage = totalCount / everyPage + 1;
		}
		return totalPage;
	}

	private static int getCurrentPage(int currentPage) {
		return currentPage == 0 ? 1 : currentPage;
	}

	private static int getEveryPage(int everyPage) {
		return everyPage == 0 ? 10 : everyPage;
	}
}
