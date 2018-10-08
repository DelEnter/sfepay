package com.ecpss.action.pay.test;

public class TestIngo {
	
public  static void mail(String[] arga){
	
	
	
}	
	
	public String GetSqlIn(String sqlParam, String columnName )

    {

        int width = sqlParam.indexOf("'", 1 )-1;

//       String temp = String.Empty;
        String temp = "";
        for( int i = 0; i <sqlParam.length(); i += 1000 * ( width + 3 ) )

        {

            if( i + 1000 * ( width + 3 ) - 1 < sqlParam.length())

            {

                temp = temp + sqlParam.substring( i, 1000 * ( width + 3 ) - 1 )

                    + ") OR " + columnName + " IN (";

            }

            else

            {

                temp = temp + sqlParam.substring( i, sqlParam.length() - i );

            }

        }



        return temp;

    } 


}
