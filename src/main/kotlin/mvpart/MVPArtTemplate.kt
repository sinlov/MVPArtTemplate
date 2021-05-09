package mvpart

import com.android.tools.idea.wizard.template.*
import java.io.File

/**
 * MVPArt Template config
 */
val MVPArtTemplate
    get() = template {
        revision = 1
        name = "MVPArtTemplate"
        minApi = 9
        minBuildApi = 15
        description = "Create all the components needed for a single page of MVPart"

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val pageName = stringParameter {
            name = "Page Name"
            default = "Example"
            help = "请填写页面名,如填写 Main,会自动生成 MainActivity, MainPresenter 等文件"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        val packageName = stringParameter {
            name = "Root Package Name"
            default = "com.mycompany.myapp"
            constraints = listOf(Constraint.PACKAGE)
            help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:me.jessyan.arms"
        }

        /**
         * 是否需要生成 Activity
         */
        val needActivity = booleanParameter {
            name = "Generate Activity"
            default = true
            help = "是否需要生成 Activity ? 不勾选则不生成"
        }

        /**
         * 生成 Activity
         */
        @Suppress("RemoveSingleExpressionStringTemplate")
        val activityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_main"
            visible = { needActivity.value }
            help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(pageName.value.toLowerCase())}" }
        }

        /**
         * 是否需要 Activity 布局
         */
        val generateActivityLayout = booleanParameter {
            name = "Generate Activity Layout"
            default = true
            visible = { needActivity.value }
            help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
        }

        /**
         * Activity 包名设置
         */
        val activityPackageName = stringParameter {
            name = "Activity Package Name"
            default = "Activity Package Name"
            visible = { needActivity.value }
            help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { "${packageName.value}.mvp.ui.activity" }
        }

        /**
         * 是否需要生成 Fragment
         */
        val needFragment = booleanParameter {
            name = "Generate Fragment"
            default = false
            help = "是否需要生成 Fragment ? 不勾选则不生成"
        }

        /**
         * 生成 Fragment 布局名
         */
        @Suppress("RemoveSingleExpressionStringTemplate")
        val fragmentLayoutName = stringParameter {
            name = "Fragment Layout Name"
            default = "fragment_main"
            visible = { needFragment.value }
            help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${fragmentToLayout(pageName.value.toLowerCase())}" }
        }

        /**
         * 是否需要 Fragment 布局
         */
        val generateFragmentLayout = booleanParameter {
            name = "Generate Fragment Layout"
            default = true
            visible = { needFragment.value }
            help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
        }

        /**
         * 生成 Fragment 包名
         */
        val fragmentPackageName = stringParameter {
            name = "Fragment Package Name"
            default = "function Package Name"
            constraints = listOf(Constraint.PACKAGE)
            visible = { needFragment.value }
            help = "Fragment 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            suggest = { "${packageName.value}.mvp.ui.fragment" }
        }

        /**
         * 是否需要生成 Presenter
         */
        val needPresenter = booleanParameter {
            name = "Generate Presenter"
            default = true
            help = "是否需要生成 Presenter ? 不勾选则不生成"
        }

        /**
         * Presenter name
         */
        val presenterName = stringParameter {
            name = "Presenter Name"
            default = "Example"
            constraints = listOf(Constraint.NONEMPTY)
            visible = { needPresenter.value }
            help = "请填写 Presenter 名(若此栏为空,则 Activity/Fragment 不会引用任何 Presenter)"
            suggest = { "${pageName.value}Presenter" }
        }

        /**
         * Presenter packageName
         */
        val presenterPackageName = stringParameter {
            name = "Presenter Package Name"
            default = "Presenter Package Name"
            constraints = listOf(Constraint.PACKAGE)
            visible = { needPresenter.value }
            help = "Presenter 将被输出到此包下, 请认真核实此包名是否是你需要输出的目标包名"
            suggest = { "${packageName.value}.mvp.presenter" }
        }

        /**
         * 是否需要生成 Model
         */
        val needModel = booleanParameter {
            name = "Generate Model"
            default = true
            help = "是否需要生成 Model ? 不勾选则不生成"
        }

        val modelName = stringParameter {
            name = "Model Name"
            default = "Example"
            constraints = listOf(Constraint.NONEMPTY)
            visible = { needPresenter.value }
            help = "请填写 Model 名(若此栏为空,则 Presenter 不会引用任何 Model)"
            suggest = { "${pageName.value}Repository" }
        }

        val modelPackageName = stringParameter {
            name = "Model Package Name"
            default = "Model Package Name"
            constraints = listOf(Constraint.PACKAGE)
            visible = { needModel.value }
            help = "Model 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            suggest = { "${packageName.value}.mvp.model" }
        }

        widgets(
            TextFieldWidget(pageName),
            LanguageWidget(),
            PackageNameWidget(packageName),

            CheckBoxWidget(needActivity),
            TextFieldWidget(activityLayoutName),
            CheckBoxWidget(generateActivityLayout),
            TextFieldWidget(activityPackageName),

            CheckBoxWidget(needFragment),
            TextFieldWidget(fragmentLayoutName),
            CheckBoxWidget(generateFragmentLayout),
            TextFieldWidget(fragmentPackageName),

            CheckBoxWidget(needPresenter),
            TextFieldWidget(presenterName),
            TextFieldWidget(presenterPackageName),

            CheckBoxWidget(needModel),
            TextFieldWidget(modelName),
            TextFieldWidget(modelPackageName)
        )


        thumb { File("template_blank_activity.png") }

        recipe = { data: TemplateData ->
            mvpartRecipe(
                data as ModuleTemplateData,
                pageName.value,
                packageName.value,
                needActivity.value,
                activityLayoutName.value,
                generateActivityLayout.value,
                activityPackageName.value,
                needFragment.value,
                fragmentLayoutName.value,
                generateFragmentLayout.value,
                fragmentPackageName.value,
                needPresenter.value,
                presenterName.value,
                presenterPackageName.value,
                needModel.value,
                modelName.value,
                modelPackageName.value
            )
        }
    }