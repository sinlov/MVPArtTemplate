package mvpart.src.app.kotlin

fun artPresenterKT(
        packageName: String,
        pageName: String,
        presenterName: String,
        presenterPackageName: String,
        needModel: Boolean,
        modelName: String,
        modelPackageName: String
) = """
package ${presenterPackageName}

import me.jessyan.art.di.component.AppComponent
import me.jessyan.art.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler

${if (needModel) "import ${modelPackageName}.${modelName}" else ""}

class ${presenterName}(appComponent:AppComponent) :
    BasePresenter ${if (needModel) "<${modelName}>" else ""}${if (needModel) "appComponent.repositoryManager().createRepository(${modelName}::class.java)" else ""} {

    private val mErrorHandler by lazy{
        appComponent.rxErrorHandler()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
"""