package com.tandong.sademo.avatar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.avatars.AvatarDrawableFactory;
import com.tandong.sa.topbar.TopBar;
import com.tandong.sa.topbar.TopBar.Action;
import com.tandong.sademo.R;

public class AvatarActivity extends SmartActivity {
	private TopBar tb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar);
		initTop();
		AvatarDrawableFactory avatarDrawableFactory = new AvatarDrawableFactory(getResources(), this);

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inMutable = false;

		Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options);

		Drawable roundedAvatarDrawable = avatarDrawableFactory.getRoundedAvatarDrawable(avatar);
		ImageView roundedAvatarView = (ImageView) this.findViewById(R.id.roundedAvatar);
		roundedAvatarView.setImageDrawable(roundedAvatarDrawable);

		Drawable borderedRoundedAvatarDrawable = avatarDrawableFactory.getBorderedRoundedAvatarDrawable(avatar);
		ImageView borderedRoundedAvatarView = (ImageView) this.findViewById(R.id.borderedRoundedAvatar);
		borderedRoundedAvatarView.setImageDrawable(borderedRoundedAvatarDrawable);

		Drawable squaredAvatarDrawable = avatarDrawableFactory.getSquaredAvatarDrawable(avatar);
		ImageView squaredAvatarView = (ImageView) this.findViewById(R.id.squaredAvatar);
		squaredAvatarView.setImageDrawable(squaredAvatarDrawable);

		Drawable doubleSquaredAvatarDrawable = avatarDrawableFactory.getSquaredAvatarDrawable(avatar, avatar);
		ImageView doubleSquaredAvatarView = (ImageView) this.findViewById(R.id.doubleSquaredAvatar);
		doubleSquaredAvatarView.setImageDrawable(doubleSquaredAvatarDrawable);

		Drawable tripleSquaredAvatarDrawable = avatarDrawableFactory.getSquaredAvatarDrawable(avatar, avatar, avatar);
		ImageView tripleSquaredAvatarView = (ImageView) this.findViewById(R.id.tripleSquaredAvatar);
		tripleSquaredAvatarView.setImageDrawable(tripleSquaredAvatarDrawable);

		Drawable quadrupleSquaredAvatarDrawable = avatarDrawableFactory.getSquaredAvatarDrawable(avatar, avatar,
				avatar, avatar);
		ImageView quadrupleSquaredAvatarView = (ImageView) this.findViewById(R.id.quadrupleSquaredAvatar);
		quadrupleSquaredAvatarView.setImageDrawable(quadrupleSquaredAvatarDrawable);
	}

	private void initTop() {
		tb = (TopBar) this.findViewById(R.id.topbar);
		tb.setTitle("头像处理引擎");

		tb.setHomeAction(new Action() {

			@Override
			public void performAction(View view) {
				AvatarActivity.this.finish();
			}

			@Override
			public int getDrawable() {

				return R.drawable.back;
			}
		});

	}
}
