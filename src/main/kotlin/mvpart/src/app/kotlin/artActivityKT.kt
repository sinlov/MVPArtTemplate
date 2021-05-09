package mvpart.src.app.kotlin

fun artActivityKT(
        packageName: String,
        pageName: String,
        activityPackageName: String,
        activityLayoutName: String,
        needPresenter: Boolean,
        presenterName: String,
        presenterPackageName: String
) = """
package ${activityPackageName}

import android.os.Bundle

import me.jessyan.art.mvp.IPresenter
import me.jessyan.art.mvp.IView
import me.jessyan.art.mvp.Message
import me.jessyan.art.utils.ArtUtils

${if (needPresenter) "import ${presenterPackageName}.${presenterName}" else ""}

class ${pageName}Activity : BaseActivity${if (needPresenter) "<${presenterName}>" else ""}, IView {

    override fun initView(savedInstanceState:Bundle?):Int {
        //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.${activityLayoutName}
    }

    override fun initData(savedInstanceState:Bundle?) {

    }

    override fun obtainPresenter():${if (needPresenter) presenterName else ""}? {
        return ${if (needPresenter) "${presenterName}(ArtUtils.obtainAppComponentFromContext(this))" else "null"}
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message:String) {
        ArtUtils.snackbarText(message)
    }

    override fun handleMessage(message:Message) {
         when (message.what) {
             0 -> {
                 //根据what 做你想做的事情
             }
             else -> {
             }
         }
     }
}
"""