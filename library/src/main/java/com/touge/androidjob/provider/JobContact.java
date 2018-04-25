package com.touge.androidjob.provider;

import android.net.Uri;

public class JobContact {
    public static final String CONTENT_AUTHORITY = "com.tougee.android-job.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    
    public static final String PATH_JOB = "job";

    public interface JobColumns {
        String ID = "id";
        String PRIORITY = "priority";
        String RETRY_COUNT = "retry_count";
        String REQUIRE_TYPE = "require_type";
        String GROUP_ID = "group_id";
    }

    public static class Jobs implements JobColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_JOB).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".job";
        public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".job";

        public static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString() + "/" + PATH_JOB);

        public static Uri buildJobUri(String id) {
            return CONTENT_URI.buildUpon().appendEncodedPath(id).build();
        }

        public static String getJobId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
