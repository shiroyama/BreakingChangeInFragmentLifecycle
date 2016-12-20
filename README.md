# Breaking Change in Fragment's Life Cycle

From support library 25.1.0, Fragment's life cycle has been changed when replacing Fragments.

```
D/FragmentA: onStart()
D/FragmentA: onResume()
D/FragmentB: onStart()
D/FragmentB: onResume()
D/FragmentA: onPause()
D/FragmentA: onStop()
```

[Issue 230415](https://code.google.com/p/android/issues/detail?id=230415)

This is an intended behavior change.
You can use `FragmentTransition#setAllowOptimization(false)` if you prefer the past behavior.

```
getFragmentManager()
        .beginTransaction()
        .setAllowOptimization(false)
        .replace(R.id.container, FragmentA.newInstance(), FragmentA.class.getSimpleName())
        .commit();
```
