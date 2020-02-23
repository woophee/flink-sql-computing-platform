import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: () => import("@/views/View.vue"),
    children: [
      {
        path: "/",
        name: "home",
        component: () => import("@/views/home/Home.vue")
      },
      {
        path: "/group",
        name: "group",
        component: () => import("@/views/define/Group.vue")
      },
      {
        path: "/realtime-job",
        name: "realtime-job",
        component: () => import("@/views/define/RealtimeJob.vue")
      },
      {
        path: "/scheduled-job",
        name: "scheduled-job",
        component: () => import("@/views/define/ScheduledJob.vue")
      },
      {
        path: "/status-bycluster",
        name: "status-bycluster",
        component: () => import("@/views/status/StatusByCluster.vue")
      },
      {
        path: "/realtime-status",
        name: "realtime-status",
        component: () => import("@/views/status/RealtimeStatus.vue")
      },
      {
        path: "/scheduled-status",
        name: "scheduled-status",
        component: () => import("@/views/status/ScheduledStatus.vue")
      },
      {
        path: "/cluster-list",
        name: "cluster-list",
        component: () => import("@/views/cluster/ClusterList.vue")
      },
      {
        path: "/cluster-manage",
        name: "cluster-manage",
        component: () => import("@/views/cluster/ClusterManage.vue")
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
