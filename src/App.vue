<template>
  <binaryData></binaryData>
  <ASCII></ASCII>
</template>

<script>
import ASCII from './components/ASCII.vue';
import binaryData from './components/binaryData.vue'

export default {
  name: 'App',
  components: {
    binaryData,
    ASCII
}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
