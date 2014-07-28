package com.tandong.sademo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.tandong.sademo.entity.New;

public class SortTime implements Comparator {

	@Override
	public int compare(Object x, Object y) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		New px = (New) x;
		New py = (New) y;
		try {
			Date dx = sdf.parse(px.getTime());
			Date dy = sdf.parse(py.getTime());
			return dy.compareTo(dx);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
