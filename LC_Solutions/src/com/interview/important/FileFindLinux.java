package com.interview.important;
import java.util.ArrayList;
import java.util.List;

public class FileFindLinux {
	public static void main(String[] args) {
		// Assume this is the root directory
		File root = new File();
		// Add all filters
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new MinSizeFilter(5));
		filters.add(new ExtensionFilter("xml"));
		List<File> list = findWithFilters(root, filters);
		System.out.println(list);
	}

	public static List<File> findWithFilters(File directory, List<Filter> filters) {
		if (!directory.isDirectory) {
			return null;
		}
		List<File> list = new ArrayList<File>();
		findWithFilters(directory, filters, list);
		return list;
	}

	public static void findWithFilters(File directory, List<Filter> filters, List<File> output) {
		if (directory.children == null) {
			return;
		}
		for (File file : directory.children) {
			if (directory.isDirectory) {
				findWithFilters(file, filters, output);
			} else {
				boolean filePassed = true;
				for (Filter filter : filters) {
					if (!filter.applyFilter(file)) {
						filePassed = false;
						break;
					}
				}
				if (filePassed) {
					output.add(file);
				}
			}

		}
	}

}

class File {
	String name;
	int size;
	boolean isDirectory;
	File[] children;
}

abstract class Filter {
	abstract boolean applyFilter(File file);
}

class MinSizeFilter extends Filter {
	int minSize;

	MinSizeFilter(int minSize) {
		this.minSize = minSize;
	}

	@Override
	boolean applyFilter(File file) {
		return file.size >= minSize;
	}
}

class ExtensionFilter extends Filter {
	String extension;

	ExtensionFilter(String extension) {
		this.extension = extension;
	}

	@Override
	boolean applyFilter(File file) {
		return file.name.endsWith("." + extension);
	}
}
