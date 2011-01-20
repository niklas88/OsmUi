package de.osmui.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PipeImExShFilter extends FileFilter{
    
	public String getExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
	@Override
	public boolean accept(File file) {
	        if (file.isDirectory()) {
	            return true;
	        }

	        String extension = getExtension(file);
	        if (extension != null) {
	            if (extension.equals("sh")) {
	                    return true;
	            } else {
	                return false;
	            }
	        }

	        return false;
	    }

	    //The description of this filter
	    public String getDescription() {
	        return ".sh";
	    }

}