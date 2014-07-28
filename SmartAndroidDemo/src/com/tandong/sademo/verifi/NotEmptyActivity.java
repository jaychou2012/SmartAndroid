package com.tandong.sademo.verifi;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.core.SmartCore;
import com.tandong.sa.topbar.ActionBar;
import com.tandong.sa.topbar.ActionBar.Action;
import com.tandong.sa.verifi.ConfirmVerifi;
import com.tandong.sa.verifi.EmailVerifior;
import com.tandong.sa.verifi.Form;
import com.tandong.sa.verifi.NotEmptyVerifior;
import com.tandong.sa.verifi.OrTwoRequiredVerifi;
import com.tandong.sa.verifi.UrlVerifior;
import com.tandong.sa.verifi.Verifi;
import com.tandong.sademo.R;

public class NotEmptyActivity extends SmartActivity implements OnClickListener {

	private EditText et_notEmpty;
	private EditText et_orRequired1;
	private EditText et_email;
	private EditText et_password;
	private EditText et_confirmPassword;
	private EditText et_url;
	private Button btn_ok;

	private Form mForm;

	private ActionBar ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		;
		setContentView(SmartCore.smartResourceId(this, "activity_verifi",
				"layout"));
		initTop();

		et_notEmpty = (EditText) findViewById(R.id.et_notEmpty);
		et_orRequired1 = (EditText) findViewById(R.id.orrequired1);
		et_email = (EditText) findViewById(R.id.email);
		et_password = (EditText) findViewById(R.id.password1);
		et_confirmPassword = (EditText) findViewById(R.id.password2);
		et_url = (EditText) findViewById(R.id.url);
		btn_ok = (Button) findViewById(R.id.btn_ok);

		// 创建表单
		mForm = new Form();

		// 非空
		Verifi et_notEmpty_verifi = new Verifi(et_notEmpty);
		et_notEmpty_verifi.addValidator(new NotEmptyVerifior(this));

		// 2选1必填
		OrTwoRequiredVerifi orRequiredVal = new OrTwoRequiredVerifi(
				et_orRequired1, et_email);

		// 邮箱
		Verifi emailField = new Verifi(et_email);
		EmailVerifior mailVal = new EmailVerifior(this);
		// 设置邮箱格式，可以不设置
		mailVal.setDomainName("gmail\\.com");
		emailField.addValidator(mailVal);

		// 重复密码
		ConfirmVerifi confirmFields = new ConfirmVerifi(et_password,
				et_confirmPassword);

		// 网址
		Verifi urlValidator = new Verifi(et_url);
		urlValidator.addValidator(new UrlVerifior(this));

		// 加入到表单
		mForm.addValidates(et_notEmpty_verifi);
		mForm.addValidates(orRequiredVal);
		mForm.addValidates(emailField);
		mForm.addValidates(confirmFields);
		mForm.addValidates(urlValidator);

		btn_ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			
			// 进行表单验证
			if (mForm.validate()) {
				showToast("Valid Form！验证通过");
			}
			break;

		default:
			break;
		}

	}

	private void initTop() {
		disableSoftkeyBoardAutoShow();
		ab = (ActionBar) findViewById(R.id.actionbar);
		ab.setTitle("非空验证框架");
		ab.setHomeAction(new Action() {

			@Override
			public void performAction(View arg0) {
				NotEmptyActivity.this.finish();
			}

			@Override
			public int getDrawable() {

				return R.drawable.back;
			}
		});

	}

}
