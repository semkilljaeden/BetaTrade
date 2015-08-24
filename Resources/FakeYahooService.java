package com.citi.trading.marketdata;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
At your option, you may use this as a backup for the Yahoo! service --
just in case there are any network failures during demo time.
You should be able to drop this class into your existing RESTful 
web-service project, and if you are using an explicit application class
you will need to add a line to publish this alongside your existing
root resource class. Then tweak your client code to hit
    http://localhost:8080/<your-webapp>/<your-root>/Yahoo/quotes.csv
instead of the http://download.finance.etc. URL.

See the method comments for a few more specifics.
 
@author Will Provost
*/
@Path("Yahoo")
public class FakeYahooService
{
    /**
    Want to demo a ticker that isn't shown below? Just add another line.
    Or expect it to trade at prices hovering around 100.
    */
    private double getBasePrice (String ticker)
    {
        if (ticker.equalsIgnoreCase ("AAPL")) return 95;
        if (ticker.equalsIgnoreCase ("GOOG")) return 560;
        if (ticker.equalsIgnoreCase ("BRK-A")) return 100000;
        if (ticker.equalsIgnoreCase ("NSC")) return 194000;
        if (ticker.equalsIgnoreCase ("MSFT")) return 43;

        //TODO hash ticker and come up with a starting price
        return 100;
    }
    
    public double getMarketPrice (String ticker, long seed)
    {
        final long PERIOD_LONG = 2 * 60 * 1000; // two minutes
        final long PERIOD_SHORT = PERIOD_LONG * 3 / 20;
        
        double base = getBasePrice (ticker);
        double magnitudeLong = base * 0.05;
        double magnitudeShort = magnitudeLong * 0.10;
        
        double varianceLong = 
            Math.sin (Math.PI * 2 * (seed % PERIOD_LONG) / PERIOD_LONG) * magnitudeLong;
        double varianceShort = 
            Math.sin (Math.PI * 2 * (seed % PERIOD_SHORT) / PERIOD_SHORT) * magnitudeShort;
        
        return base + varianceLong + varianceShort;
    }
    
    public double getMarketPriceNow (String ticker)
    {
        return getMarketPrice (ticker, System.currentTimeMillis ());
    }
    
    /**
    Supports price as l1, ask/bid as a0/b0, ticker as s0, and volume as v0.
    Ask, bid, and price are all the same number, every time.
    v0 is always 1000.
    You can add other values here as you like.
    */
    @GET
    @Path("quotes.csv")
    public String getMarketData
    (
        @QueryParam ("s") @DefaultValue ("") String s,
        @QueryParam ("f") @DefaultValue ("") String f
    )
    {
        if (s.length () == 0 || f.length () == 0 || f.length () % 2 == 1)
            throw new WebApplicationException 
                (Response.status (Status.BAD_REQUEST)
                         .entity ("s and f must each have values.")
                         .build ());
            
        String[] stocks = s.split(",");
        String[] fields = new String[f.length () / 2];
        for (int i = 0; i < f.length () / 2; ++i)
            fields[i] = f.substring (i * 2, i * 2 + 2);
        
        StringBuilder result = new StringBuilder ();
        
        for (String stock : stocks)
        {
            for (String field : fields)
            {
                if (field.equals ("s0")) 
                    result.append ('"')
                          .append (stock)
                          .append ('"');
                else if (field.equals ("p0") || field.equals ("l1") ||
                          field.equals ("a0") || field.equals ("b0")) 
                    result.append 
                        (String.format ("%1.2f", getMarketPriceNow (stock)));
                else if (field.equals ("v0"))
                    result.append ("1000");
                else
                    result.append ("N/A");
                
                result.append (",");
            }
            result.deleteCharAt (result.length () - 1)
                  .append ("\n");
        }
        
        return result.toString ();
    }
}
