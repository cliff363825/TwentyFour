<?php

require_once __DIR__ . '/Observer.php';
require_once __DIR__ . '/Subject.php';
require_once __DIR__ . '/ConcreteObserver.php';
require_once __DIR__ . '/ConcreteSubject.php';

// 构建目标对象（主题对象）
$subject = new ConcreteSubject();
// 具体的观察者
$ob1 = new ConcreteObserver();
$ob2 = new ConcreteObserver();
$ob3 = new ConcreteObserver();
$ob1->setState(10); // 设置观察者1的状态为10
$ob2->setState(20); // 设置观察者1的状态为20
$ob3->setState(30); // 设置观察者1的状态为30
// 将三个观察者加入到观察者队列中
$subject->registerObserver($ob1);
$subject->registerObserver($ob2);
$subject->registerObserver($ob3);
// 查看未修改是的状态
echo $ob1->getState() . "\n";
echo $ob2->getState() . "\n";
echo $ob3->getState() . "\n";

echo "-------修改后的状态------\n";
// 改变目标对象的状态
$subject->setState(1000);
// 查看观察者对象的状态
echo $ob1->getState() . "\n";
echo $ob2->getState() . "\n";
echo $ob3->getState() . "\n";
