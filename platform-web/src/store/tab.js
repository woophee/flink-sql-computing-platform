export default {
  state: {
    isCollapse: false,
    currentMenu: null,
    menu: [],
    tabList: [
      {
        path: "/",
        name: "home",
        label: "首页",
        icon: "home"
      }
    ]
  },
  mutations: {
    selectMenu(state, val) {
      if (val.name !== "home") {
        state.currentMenu = val;
        let result = state.tabList.findIndex(item => item.name === val.name);
        result === -1 ? state.tabList.push(val) : "";
      } else {
        state.currentMenu = null;
      }
    },
    closeTab(state, val) {
      let result = state.tabList.findIndex(item => item.name === val.name);
      state.tabList.splice(result, 1);
    },
    collapseMenu(state) {
      state.isCollapse = !state.isCollapse;
    }
  },
  actions: {},
  modules: {}
};
