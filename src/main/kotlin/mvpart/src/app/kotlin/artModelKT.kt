package mvpart.src.app.kotlin

import com.sinlov.kotlin.utils.DateUtil

fun artModelKT(
        packageName: String,
        pageName: String,
        modelName: String,
        modelPackageName: String,
        needPresenter: Boolean,
        presenterName: String,
        presenterPackageName: String
) = """
package ${modelPackageName}

import me.jessyan.art.mvp.IModel
import me.jessyan.art.mvp.IRepositoryManager

/**
 * ================================================
 * 必须实现 IModel
 * 可以根据不同的业务逻辑划分多个 Repository 类, 多个业务逻辑相近的页面可以使用同一个 Repository 类
 * 无需每个页面都创建一个独立的 Repository
 * 通过 {@link me.jessyan.art.mvp.IRepositoryManager#createRepository(java.lang.Class)} 获得的 Repository 实例, 为单例对象
 * <p>
 * Created on ${DateUtil.nowDateStr()}
 * by <a href="https://github.com/sinlov/MVPArtTemplate">https://github.com/sinlov/MVPArtTemplate</a>
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArt">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
class ${modelName}(manager:IRepositoryManager) : IModel {

    override fun onDestroy() {
        
    }
}
"""