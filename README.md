# JFinal_Demo
JFinal 2.0 简陋版增删改查 仅适用于本人初学....类型

后续发现的问题：

  1）读取配置文件的方式可以使用JFinal自带的PropKit的方法进行读取 如在MyCookie,MySession,MyId之间的类中
  
  2）在全局拦截器中 合法的URL 可以使用@Clear进行清除拦截 而不是需要进行匹配才准放行
  
  3）在拿取全部Model的时候 不需要set 一个一个的取 应该选择使用getModel进行整个取


| 问题                                                         |        |
| ------------------------------------------------------------ | ------ |
| css等样式都重复使用 需要修改一下 引用地址                    | 优化   |
| favicon.ioc 在拦截器中放行该请求 并使其在静态资源中查询是否有该图标 | 未解决 |
| freemarker 重复使用部分 需要抽取出来                         | 优化   |
| 界面左上角 取Cookie值 内部使用 ${user!""} 的方式 但是需要在每个地方都设置一遍 不妥 | 未解决 |
| 业务层代码 要和 控制层 代码分离                              | 小优化 |
| url 全局拦截器 并没有实现好                                  | 优化   |
| 代码过于繁琐 无用代码需要进行优化                            | 小优化 |
| JFinal自身的校验工具 其中的message信息 传递不出 或者说使用错误 | 解决   |
| 编辑界面 如果框中没有设置值  自动使用回显的数据 但是如果提交错误 后续的需要全部填写....默认值会消失 | 未解决 |
| 项目重启之后 之前的网页 随便访问一个 按理是出现请登录 但很多时候出现密码错误 | 解决   |
| 如果想要拦截其他请求 进行重定向的时候 如果在路由中进行了设置 默认会在其下文件中查找静态资源文件 但是本身位置并不在其中 如果解决 暂时只想到把所有界面放在一起 | 优化   |
| me.add(new UrlSkipHandler(".*html",false)); 怎样提示不能访问静态资源的信息 或者可以使用Handel拦截并返回信息 | 未解决 |
| 数据校验 前后都要做 但这里只进行某些值的低级校验 不妥        | 小优化 |
| 进行编辑操作 会新增一个窗口 而不是在原有窗口中进行编辑操作   | 解决   |
| 将添加和修改整合为一个界面 然后其中使用if elseif else 进行判断 | 优化   |

