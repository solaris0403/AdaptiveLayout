package tony.adaptivelayout.attrs;

/**
 * Created by tony on 7/18/16.
 */
public interface Attrs {
    int WIDTH           = 0x01;
    int HEIGHT          = 0x01 << 1;
    int MARGIN          = 0x01 << 2;
    int MARGIN_LEFT     = 0x01 << 3;
    int MARGIN_TOP      = 0x01 << 4;
    int MARGIN_RIGHT    = 0x01 << 5;
    int MARGIN_BOTTOM   = 0x01 << 6;
    int PADDING         = 0x01 << 7;
    int PADDING_LEFT    = 0x01 << 8;
    int PADDING_TOP     = 0x01 << 9;
    int PADDING_RIGHT   = 0x01 << 10;
    int PADDING_BOTTOM  = 0x01 << 11;
    int MIN_WIDTH       = 0x01 << 12;
    int MAX_WIDTH       = 0x01 << 13;
    int MIN_HEIGHT      = 0x01 << 14;
    int MAX_HEIGHT      = 0x01 << 15;
    int TEXT_SIZE       = 0x01 << 16;
}
