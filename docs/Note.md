According to official docs...

### @Component

> But instead of us writing the implementation, we can annotate it with @Component
> to have Dagger generate an implementation for us.

> @Component tells Dagger to implement an interface or abstract class
> that creates and returns one or more application objects.
> Dagger will generate a class that implements the component type.
> The generated type will be named DaggerYourType (or DaggerYourType_NestedType for nested types)

- Responsible for providing dependencies to other classes.
- Just a simple way to implement factory pattern.

#### @Subcomponent

> A @Subcomponent is similar to a @Component: it has abstract methods that Dagger implements,
> and it can use @Modules. It always has a parent component, and it can access any type that
> the parent component can access. Any types it creates are hidden from the parent component.

> We have a module that declares the subcomponent. Including this module in another component
> will make the @Subcomponent.Factory available there. That’s our bridge between the two components.

- Subcomponent always has a parent component.
- It can access types that parent component can access - It *inherits* parent component.
- Parent component cannot access its subcomponent.
- We can connect component and subcomponent using a module, which declares the subcomponent.

-> Subcomponent can be used as a encapsulation mechanism in Dagger module system.

#### @Component.Factory / @Subcomponent.Factory

> The @Factory annotation annotates a factory type for this component.
> It’s an interface we define.

> A @Factory-annotated type creates instances of the component.
> If it is a subcomponent, an  instance of it is requestable in the parent component.

- Factory is used to create component/subcomponent
- Factory of subcomponent is requestable in, or can be injected in parent component.

#### @BindsInstance

> Factory has a single method that creates an instance of the component/subcomponent.
> That method may have a @BindsInstance parameter, which tells Dagger that
> the Account instance we pass as an argument should be requestable
> by any @Inject constructor, @Binds method, or @Provides method in this component/subcomponent.

- It binds an instance of the type within the component/subcomponent.
- A type annotated with @BindsInstance, is requestable in the component.


### @Inject

> The @Inject annotation indicates to Dagger that when we ask for a CommandRouter,
> Dagger should call new CommandRouter().

> @Inject on a constructor tells Dagger how to instantiate that class. We’ll see more shortly.

> Parameters to an @Inject constructor are the dependencies of the class.
> Dagger will provide a class’s dependencies to instantiate the class itself.
> Note that this is recursive: a dependency may have dependencies of its own!

- Inject is a connection between user of the dependency, and the provider - Component.
- Inject is an edge between nodes in dependency graph.
- Dependency resolving (instantiating the class) process is recursive.


### @Binds

> This @Binds method tells Dagger that when something depends on a Command,
> Dagger should provide a HelloWorldCommand object in its place.

> @Binds methods are one way to tell Dagger how to construct an instance.
> They are abstract methods on modules that associate one type that
> Dagger already knows how to construct (the method’s parameter)
> with a type that Dagger doesn’t yet know how to construct (the method’s return type).

- It tells Dagger how to construct an instance.
- It "binds" the parameter type and the return type - when someone request return type,
  then it provides an instance of return type, automatically.
  There should be an instruction on how to build an instance of return type, of course,
  something like @Inject constructor or @Provides
- Binds must be inside a module.

#### Multi-bindings using @IntoMap

> The @StringKey annotation, combined with @IntoMap,
> tells Dagger how to populate a Map<String, Command>.

- It is possible to bind several implementations for same polymorphic type.
- There's no magic. It just tells Dagger how to populate Map<KeyType, ValueType>.
- We can request map of instances, instead of a single instance.

#### @BindsOptionalOf

> When Dagger sees a @BindsOptionalOf method, it will use Optional.of(<the account>) if an Account is available;
> if not, it will use Optional.empty() (or Optional.absent() if you’re using the Guava version of Optional).

- When there's a binding for the type, it returns Optional.of(instance).
- When there's no binding for the type, it returns Optional.empty()


### @Provides

> A @Provides method works a lot like an @Inject constructor:
> here it tells Dagger that when it needs an instance of Outputter,
> it should call SystemOutModule.textOutputter() to get one.

> @Provides methods can contain arbitrary code as long as they return an instance of the provided type.
> They do not need to create a new instance on each invocation.

- It tells Dagger how to construct an instance.
- It tells Dagger that when something requests an instance of the type the method returns, it should call that method to get an instance.
- @Provides works very similar to @Inject constructor


### @Modules

> Modules are collections of binding methods
> that give Dagger instructions on how to provide instances.

> @Modules are classes or interfaces that act as collections of instructions for Dagger
> on how to construct dependencies. They’re called modules because they are modular:
> you can mix and match modules in different applications and contexts.

- Modules are classes/interfaces that give us some instructions on how to construct dependencies.
- Modules are modular, so you can mix them in different applications.


### Scope

> Dagger by default provides one Database object when LoginCommand requests it
> and another when DepositCommand requests it.

> In order to tell Dagger that they both need to share the same instance of Database,
> we annotate the Database class with @Singleton.
> We also annotate our @Component type with @Singleton to declare
> that instances of classes annotated with @Singleton should be shared
> among other objects that depend on them in this component.

- @Singleton tells Dagger to create only single instance, and share it.
- @Singleton can be used on the declaration of class that has an @Inject constructor, or @Binds or @Provides methods.
- We should also annotate component with @Singleton,
  in order to share an instance annotated with @Singleton among other objects in that component.


### @Qualifier

> To differentiate between different things of the same Java type in a case like this, we use qualifiers.
> A qualifier is an annotation that can be used to give Dagger information it can use
> to distinguish between instances of the same type.
> Qualifiers are annotations that are themselves annotated with @Qualifier.

> Qualifiers are often, but certainly not always, used with common data types
> such as primitive types and String, which may be used in many places in a program for very different reasons.

- Qualifiers are annotations, annotated with @Qualifier.
- Qualifiers are applied to @Provides or @Binds methods & injection site, to distinguish which one to use,
  when there are several provide methods that returns same type.
- Qualifiers are often used with common data types like String, Int, etc.


### @Scope

> Annotating both WithdrawalLimiter and UserCommandsRouter with @PerSession
> indicates to Dagger that a single WithdrawalLimiter should be created for every instance of UserCommandsRouter.

> Note the difference between the @Singleton Database and the @PerSession WithdrawalLimiter.
> There is one Database instance shared among all the objects in the single CommandProcessorFactory instance,
> but there is a separate instance of WithdrawalLimiter for each instance of UserCommandsRouter.

> Note that the name of the scope is meaningless;
> even multiple instances of a @Singleton-annotated type can be created
> if multiple @Singleton-annotated @Components are instantiated in a single JVM.

- Scopes are annotations, annotated with @Scope
- Scope can be applied to component/subcomponent and @Provides/@Binds methods or @Inject constructor.
- A @Scope annotation instructs Dagger to provide one shared instance for all the requests for that type
  within an instance of the (sub)component that shares the same annotation.
- The lifetime of a scoped instance is directly related to the lifetime of the component with that scope.
- @Singleton is not special. It's just one of "pre-defined" scopes.
