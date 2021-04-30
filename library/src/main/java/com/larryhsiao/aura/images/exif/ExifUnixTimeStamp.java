package com.larryhsiao.aura.images.exif;

import com.larryhsiao.clotho.Source;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static java.util.Locale.US;

/**
 * Source to build unix timestamp from exif datetime.
 * <p>
 * Note: The exif have no time zone inform, so we treat it as UTC which is no offset!
 */
public class ExifUnixTimeStamp implements Source<Long> {
    private static final SimpleDateFormat format;

    static {
        format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private final Source<String> exifDateTime;

    public ExifUnixTimeStamp(Source<String> exifDateTime) {
        this.exifDateTime = exifDateTime;
    }

    @Override
    public Long value() {
        try {
            return format.parse(exifDateTime.value()).getTime();
        } catch (ParseException e) {
            return -1L;
        }
    }
}
