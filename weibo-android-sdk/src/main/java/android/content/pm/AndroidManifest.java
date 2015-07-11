/*
 * Copyright (C) 2015 8tory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content.pm;

import android.content.Context;

public class AndroidManifest {

    Context context;

    public AndroidManifest(Context context) {
        this.context = context;
    }

    public ApplicationInfo applicationInfo() {
        return applicationInfo(context);
    }

    public static ApplicationInfo applicationInfo(Context context) {
        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        return ai;
    }

    public String metaData(String key) {
        return metaData(context, key);
    }

    private static String metaData(Context context, String key) {
        ApplicationInfo ai = applicationInfo(context);

        if (ai == null || ai.metaData == null) {
            return null;
        }

        return ai.metaData.getString(key);
    }
}
