<template>
  <div>
    <v-container
      id="scroll-target"
      style="max-height: 400px"
      class="overflow-y-auto"
    >
      <v-row
        v-scroll:#scroll-target="onScroll"
        class="d-flex flex-column justify-center align-center"
        style="height: 1000px"
      >
        <v-card height="300" width="600" v-for="(a, i) in 10" :key="i">
          {{ i }}번째 아이템
        </v-card>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    offsetTop: 0,
  }),

  methods: {
    onScroll(e) {
      this.offsetTop = e.target.scrollTop;
    },
  },
};
</script>

<style></style>
