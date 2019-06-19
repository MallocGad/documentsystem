<template>
  <a-tabs defaultActiveKey="1" style="margin: 10px;" type="card" id="ranking">
    <a-tab-pane tab="热门文件" key="1">
      <a-card id="car-list">
        <div class="item-people">
          <a href="#">文件地址</a>
          <a-badge style="zoom:0.8" count="12" :numberStyle="{backgroundColor: '#8bc34a'} "/>
        </div>
        <div class="item-people">
          <a href="#">文件地址</a>
          <a-badge style="zoom:0.8" count="10" :numberStyle="{backgroundColor: '#8bc34a'} "/>
        </div>
        <div class="item-people">
          <a href="#">文件地址</a>
          <a-badge style="zoom:0.8" count="9" :numberStyle="{backgroundColor: '#8bc34a'} "/>
        </div>
      </a-card>
    </a-tab-pane>
  </a-tabs>
</template>
