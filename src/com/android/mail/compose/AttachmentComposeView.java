/**
 * Copyright (c) 2007, Google Inc.
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
package com.android.mail.compose;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mail.providers.Attachment;
import com.android.mail.R;
import com.android.mail.utils.AttachmentUtils;
import com.android.mail.utils.LogUtils;

/**
 * This view is used in the ComposeActivity to display an attachment along with its name/size
 * and a Remove button.
 */
class AttachmentComposeView extends LinearLayout {
    private final long mSize;
    private final String mFilename;
    private final static String LOG_TAG = new LogUtils().getLogTag();

    public AttachmentComposeView(Context c, Attachment attachment) {
        super(c);
        mFilename = attachment.name;
        mSize = attachment.size;

        LogUtils.d(LOG_TAG, ">>>>> Attachment uri: %s", attachment.originExtras);
        LogUtils.d(LOG_TAG, ">>>>>           type: %s", attachment.contentType);
        LogUtils.d(LOG_TAG, ">>>>>           name: %s", mFilename);
        LogUtils.d(LOG_TAG, ">>>>>           size: %d", mSize);

        LayoutInflater factory = LayoutInflater.from(getContext());

        factory.inflate(R.layout.attachment, this);
        populateAttachmentData(c);
    }

    public void addDeleteListener(OnClickListener clickListener) {
        ImageView deleteButton = (ImageView) findViewById(R.id.remove_attachment);
        deleteButton.setOnClickListener(clickListener);
    }

    private void populateAttachmentData(Context context) {
        ((TextView) findViewById(R.id.attachment_name)).setText(mFilename);

        if (mSize != 0) {
            ((TextView) findViewById(R.id.attachment_size)).
                    setText(AttachmentUtils.convertToHumanReadableSize(context, mSize));
        } else {
            ((TextView) findViewById(R.id.attachment_size)).setVisibility(View.GONE);
        }
    }
}
