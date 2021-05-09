package mvpart.src.app.java

fun artPresenterJava(
        packageName: String,
        pageName: String,
        presenterName: String,
        presenterPackageName: String,
        needModel: Boolean,
        modelName: String,
        modelPackageName: String
) = """
package ${presenterPackageName};

import me.jessyan.art.di.component.AppComponent;
import me.jessyan.art.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

${if (needModel) "import ${modelPackageName}.${modelName};" else ""}

public class ${presenterName} extends BasePresenter${if (needModel) "<${modelName}>" else ""} {

    private RxErrorHandler mErrorHandler;

    public ${presenterName}(AppComponent appComponent) {
        ${if (needModel) "super(appComponent.repositoryManager().createRepository(${modelName}.class));" else "supper();"}
        this.mErrorHandler = appComponent.rxErrorHandler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
"""