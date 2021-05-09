package mvpart

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import mvpart.androidManifest.baseManifestTemplateXml
import mvpart.res.layout.*
import mvpart.src.app.java.artActivityJava
import mvpart.src.app.java.artFragmentJava
import mvpart.src.app.java.artModelJava
import mvpart.src.app.java.artPresenterJava
import mvpart.src.app.kotlin.artActivityKT
import mvpart.src.app.kotlin.artFragmentKT
import mvpart.src.app.kotlin.artModelKT
import mvpart.src.app.kotlin.artPresenterKT

/**
 * @CreateDate:     2021/5/5
 * @Author:         sinlov
 * @Description:    菜单
 */
fun RecipeExecutor.mvpartRecipe(
        moduleData: ModuleTemplateData,
        pageName: String,
        packageName: String,
        needActivity: Boolean,
        activityLayoutName: String,
        generateActivityLayout: Boolean,
        activityPackageName: String,
        needFragment: Boolean,
        fragmentLayoutName: String,
        generateFragmentLayout: Boolean,
        fragmentPackageName: String,
        needPresenter: Boolean,
        presenterName: String,
        presenterPackageName: String,
        needModel: Boolean,
        modelName: String,
        modelPackageName: String
) {
    val (projectData, srcOut, resOut, manifestOut) = moduleData

    val ktOrJavaExt = projectData.language.extension

    if (needActivity && needFragment) {
        throw IllegalArgumentException("activity 和 fragment不可同时选择")
    }


    if (needActivity) {
        mergeXml(
                baseManifestTemplateXml(packageName, activityPackageName, "${pageName}Activity"),
                manifestOut.resolve("AndroidManifest.xml")
        )
    }

    if (needActivity && generateActivityLayout) {
        save(ConstraintLayoutXml(), resOut.resolve("layout/${activityLayoutName}.xml"))
    }

    if (needFragment && generateFragmentLayout) {
        save(ConstraintLayoutXml(), resOut.resolve("layout/${fragmentLayoutName}.xml"))
    }


    if (needActivity) {
        if (ktOrJavaExt == "java")
            save(
                    artActivityJava(
                            packageName,
                            pageName,
                            activityPackageName,
                            activityLayoutName,
                            needPresenter,
                            presenterName,
                            presenterPackageName
                    ), srcOut.resolve("mvp/ui/activity/${pageName}Activity.${ktOrJavaExt}")
            )
        else
            save(
                    artActivityKT(
                            packageName,
                            pageName,
                            activityPackageName,
                            activityLayoutName,
                            needPresenter,
                            presenterName,
                            presenterPackageName
                    ), srcOut.resolve("mvp/ui/activity/${pageName}Activity.${ktOrJavaExt}")
            )
    }

    if (needFragment) {
        if (ktOrJavaExt == "java")
            save(
                    artFragmentJava(
                            packageName,
                            pageName,
                            fragmentPackageName,
                            fragmentLayoutName,
                            needPresenter,
                            presenterName,
                            presenterPackageName
                    ), srcOut.resolve("mvp/ui/fragment/${pageName}Fragment.${ktOrJavaExt}")
            )
        else
            save(
                    artFragmentKT(
                            packageName,
                            pageName,
                            fragmentPackageName,
                            fragmentLayoutName,
                            needPresenter,
                            presenterName,
                            presenterPackageName
                    ), srcOut.resolve("mvp/ui/fragment/${pageName}Fragment.${ktOrJavaExt}")
            )
    }

    if (needPresenter) {
        if (ktOrJavaExt == "java")
            save(
                    artPresenterJava(
                            packageName,
                            pageName,
                            presenterName,
                            presenterPackageName,
                            needModel,
                            modelName,
                            modelPackageName
                    ),
                    srcOut.resolve("mvp/presenter/${pageName}Presenter.${ktOrJavaExt}")
            )
        else
            save(
                    artPresenterKT(
                            packageName,
                            pageName,
                            presenterName,
                            presenterPackageName,
                            needModel,
                            modelName,
                            modelPackageName
                    ),
                    srcOut.resolve("mvp/presenter/${pageName}Presenter.${ktOrJavaExt}")
            )
    }


    if (needModel) {
        if (ktOrJavaExt == "java")
            save(
                    artModelJava(
                            packageName,
                            pageName,
                            modelName,
                            modelPackageName,
                            needPresenter,
                            presenterName,
                            presenterPackageName
                    ),
                    srcOut.resolve("mvp/model/${pageName}Model.${ktOrJavaExt}")
            )
        else
            save(
                    artModelKT(
                            packageName,
                            pageName,
                            modelName,
                            modelPackageName,
                            needPresenter,
                            presenterName,
                            presenterPackageName
                    ),
                    srcOut.resolve("mvp/model/${pageName}Model.${ktOrJavaExt}")
            )
    }
}