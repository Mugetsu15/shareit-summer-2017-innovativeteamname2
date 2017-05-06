package edu.hm.management.bib;

/**
 * Enumeration for the fsk of some Medium objects.
 * @author Daniel Gabl
 *
 */
public enum Fsk {
    
    FSK0(0),
    FSK6(6),
    FSK12(12),
    FSK16(16),
    FSK18(18);
    
    private final int fskLevel;
    
    /**
     * Constructor for ErrorCode Handling.
     * @param fsk Code of Result (e. g. 200, 404, etc.)
     */
    Fsk(int fsk)  {
        fskLevel = fsk;
    }
    
    /**
     * Returns the Status Code for a Response.
     * @return status code
     */
    public int getFsk()  {
        return fskLevel;
    }  
}
