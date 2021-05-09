package mvpart.src.app.kotlin

import com.sinlov.kotlin.utils.DateUtil

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

/**
 * ================================================
 * Created on ${DateUtil.nowDateStr()}
 * by <a href="https://github.com/sinlov/MVPArtTemplate">https://github.com/sinlov/MVPArtTemplate</a>
 * ================================================
 */
class ${presenterName}(appComponent: AppComponent) :
    BasePresenter ${if (needModel) "<${modelName}?>" else ""}${if (needModel) "(appComponent.repositoryManager().createRepository(${modelName}::class.java))" else ""} {

    private var mErrorHandler: RxErrorHandler?

    init {
        mErrorHandler = appComponent.rxErrorHandler()
    }

    override fun onDestroy() {
        super.onDestroy()
        mErrorHandler = null
    }
}
"""