# <u>***启命英***</u><u>***Dandelifeon更改***</u>

## 简介：

每一块函数功能不变，内部具体代码没有多少变化，更多的是函数引入形参Dandelifeon，细胞二维数组的row，col。



## 对于CellBlock类

1.删除属性row，col

2.删除获取周围细胞信息的函数，原函数功能由启命英类来实现

3.删除清除方块的函数，由系统类实现



## 对于Dandelifeon类

1.创建属性countMana用来存储总的魔力值

2.实现大部分的生命判定逻辑

```Java
public boolean lifeGameCheck()
public int getAccumulatedMana()
public int getSurround(int m_row,int m_col) //获取周围存活的细胞数量
public int getMaxAge(int m_row,int m_col) //获取周围存活的细胞最大年龄
public boolean scan() //扫描棋盘，如果细胞小于三个直接结束
```



## 对于ChessBoardWorldSystem类

1.对于大部分的函数删除了static属性（使用传参的方法）

2.生命判断函数移到Dandelifeon类中

3.设置清除方块的函数，使用默认构造的方法

4.增加check函数（原来的main函数改的），外部添加循环使游戏能重复运行

5.直接管理CellBlock和Dandelifeon两个类
