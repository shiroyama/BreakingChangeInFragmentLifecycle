# Breaking Change in FragmentTransaction at Support Library 25.1.0

In support library 25.1.0, Fragment's life cycle has been changed when replacing Fragments.

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
You can use `FragmentTransaction#setAllowOptimization(false)` if you prefer the past behavior.

```
getFragmentManager()
        .beginTransaction()
        .setAllowOptimization(false) // opt-out
        .replace(R.id.container, FragmentA.newInstance(), FragmentA.class.getSimpleName())
        .commit();
```

# FragmentTransaction's behavior reverted as of Support Library 25.1.1

As of support library 25.1.1, `setAllowOptimization(flag)` is default to false.

You can use `.setAllowOptimization(true)` only when you need optimization.

```
getFragmentManager()
        .beginTransaction()
        .setAllowOptimization(true) // opt-in
        .replace(R.id.container, FragmentA.newInstance(), FragmentA.class.getSimpleName())
        .commit();
```
