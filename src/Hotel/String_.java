package Hotel;

public class String_ {
	public static String sqlS_string(int i) {
		String cs_unum = "Create SEQUENCE SEQ_UNUM NOCACHE";
		String cs_rnum = "Create SEQUENCE SEQ_RNUM NOCACHE";
		
		String ds_unum = "DROP SEQUENCE SEQ_UNUM";
		String ds_rnum = "DROP SEQUENCE SEQ_RNUM";
		
		String s_error = "오류";
		
		if (i==1) {
			return cs_unum;
		}
		else if(i==2) {
			return cs_rnum;
		}
		else if(i==3) {
			return ds_unum;
		}
		else if(i==4) {
			return ds_rnum;
		}
		return s_error;
	}
	

}
