<?php

/**
 * Class ConcreteObserver
 * 具体的观察者
 */
class ConcreteObserver implements Observer
{
    /**
     * @var int 观察者的状态
     */
    private $state;

    /**
     * state对象需要和subject中的state保持一致
     * @param Subject $subject
     */
    public function update(Subject $subject)
    {
        /** @var ConcreteSubject $subject */
        $this->state = $subject->getState();
    }

    /**
     * @return int
     */
    public function getState()
    {
        return $this->state;
    }

    /**
     * @param int $state
     */
    public function setState($state)
    {
        $this->state = $state;
    }
}